(ns simpleui.buildlink.web.middleware.i18n)

(def ^:dynamic *lang* nil)

;; fr-CH, fr;q=0.9, en;q=0.8, de;q=0.7, *;q=0.5
(defn- default-lang [lang]
  (when lang
    (re-find #"en|tl|fil" lang)))

(defn set-lang [handler]
  (fn [req]
    (binding [*lang* (or
                      (default-lang (get-in req [:session :lang]))
                      (default-lang (get-in req [:headers "accept-language"]))
                      "en")]
      (handler req))))
