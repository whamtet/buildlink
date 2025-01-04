(ns simpleui.anchor.web.views.lang
  (:require
    [simpleui.anchor.i18n :refer [i18n]]
    [simpleui.anchor.web.htmx :refer [defcomponent]]
    [simpleui.anchor.web.middleware.i18n :as middleware.i18n]
    [simpleui.anchor.web.views.components :as components]
    [simpleui.anchor.web.views.components.dropdown :as dropdown]
    [simpleui.anchor.web.controllers.login :as controllers.login]))

(def lang-disp
  {"en" "English"
   "ja" "日本語"
   "ko" "한국어"})

(defn dropup [label m]
  [:div
   [:div {:class "drop hidden rounded-lg border p-1.5 bg-white"}
    (for [[new-lang v] m]
      [:a {:href "" :hx-get "lang-dropup" :hx-vals {:new-lang new-lang}}
       [:div {:class "p-1 hover:bg-slate-100"}
        v]])]
   (dropdown/button-up label)])

(defcomponent ^:endpoint lang-dropup [req ^:nullable new-lang]
  (if top-level?
    (controllers.login/assoc-lang (:session req) new-lang)
    [:div.absolute.bottom-0.flex.w-full.justify-center
     (dropup
      (lang-disp middleware.i18n/*lang*)
      (dissoc lang-disp middleware.i18n/*lang*))]))
