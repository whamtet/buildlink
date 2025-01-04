(ns simpleui.anchor.web.htmx
  (:require
   [simpleui.anchor.env :refer [dev?]]
   [simpleui.anchor.i18n :refer [i18n]]
   [simpleui.anchor.web.resource-cache :as resource-cache]
   [simpleui.core :as simpleui]
   [simpleui.render :as render]
   [ring.util.http-response :as http-response]
   [hiccup.core :as h]
   [hiccup.page :as p]))

(defn page [opts & content]
  (-> (p/html5 opts content)
      http-response/ok
      (http-response/content-type "text/html")))

(defn ui [opts & content]
  (-> (h/html opts content)
      http-response/ok
      (http-response/content-type "text/html")))

(defn- unminify [^String s]
  (if dev?
    (.replace s ".min" "")
    s))

(defn- scripts [js hyperscript?]
  (cond-> js
    hyperscript? (conj (unminify "https://unpkg.com/hyperscript.org@0.9.12/dist/_hyperscript.min.js"))))

(defn page-htmx [{:keys [css js hyperscript?]} & body]
  (page
   [:head
    [:meta {:charset "UTF-8"}]
    [:title (i18n "Tenderbarb")]
    [:meta {:property "og:title" :content "Tenderbarb Platform"}]
    [:meta {:property "og:type" :content "website"}]
    [:meta {:property "og:url" :content "https://tenderbarb.simpleui.io/"}]
    [:meta {:property "og:image" :content "https://tenderbarb.simpleui.io/logo.jpg"}]
    [:link {:rel "icon" :href "/logo_dark.svg"}]
    (for [sheet css]
      [:link {:rel "stylesheet" :href (resource-cache/cache-suffix sheet)}])]
   [:body
    (render/walk-attrs body)
    [:script {:src
              (unminify "https://unpkg.com/htmx.org@1.9.5/dist/htmx.min.js")}]
    [:script "htmx.config.defaultSwapStyle = 'outerHTML';"]
    (map
     (fn [src]
       [:script {:src src}])
     (scripts js hyperscript?))]))

(defmacro defcomponent
  [name [req :as args] & body]
  (if-let [sym (simpleui/symbol-or-as req)]
    `(simpleui/defcomponent ~name ~args
      (let [{:keys [~'session ~'path-params ~'query-fn]} ~sym
            ~'params (merge ~'params ~'path-params)
            {:keys [~'id]} ~'session]
        ~@body))
    (throw (Exception. "req ill defined"))))

