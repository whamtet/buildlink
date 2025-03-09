(ns simpleui.buildlink.web.views.login
  (:require
    [simpleui.buildlink.i18n :refer [i18n]]
    [simpleui.buildlink.util :refer [defcss]]
    [simpleui.buildlink.web.htmx :refer [defcomponent]]
    [simpleui.buildlink.web.views.components :as components]
    [simpleui.buildlink.web.views.lang :as lang]
    [simpleui.buildlink.web.controllers.login :as controllers.login]))

(defn- login-disp [req register first-name last-name email problem]
  [:div.pt-6.pb-12.relative.min-h-screen {:hx-target "this" :_ "on click add .hidden to .drop"}
   [:img.w-72.mx-auto {:src "/logo.jpg"}]
   [:div {:class "mt-8
   mx-auto w-1/2 p-4
   border rounded-lg overflow-hidden"}
    [:a.flex.items-center.mb-2 {:href "/api/oauth/youtube"}
     [:img.w-10.mr-4 {:src "/youtube.png"}] (i18n "Log in with Youtube")]
    ]
    ;; language selector
    (lang/lang-dropup req)])

(defmacro or-keyword [test alternative]
  `(let [~'$ ~test]
    (if (keyword? ~'$) ~alternative ~'$)))

(defcomponent ^:endpoint login [req
                                register
                                first-name
                                last-name
                                email
                                password
                                password2
                                command]
  lang/lang-dropup ;; to ensure resolution
  (case command
    "login"
    (or-keyword
     (controllers.login/login req email password)
     (login-disp req register first-name last-name email $))
    (login-disp req register first-name last-name email nil)))
