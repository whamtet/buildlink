(ns simpleui.buildlink.web.views.dashboard
  (:require
    [simpleui.buildlink.i18n :refer [i18n]]
    [simpleui.buildlink.web.htmx :refer [defcomponent]]
    [simpleui.buildlink.web.views.components.dropdown :as dropdown]
    [simpleui.buildlink.web.views.icons :as icons]
    [simpleui.buildlink.web.views.lang :as lang]))

(defn main-dropdown []
  [:div.absolute.top-1.right-1.flex.items-center
   (dropdown/dropdown
    "User"
    [[:a {:href "/"}
      [:div.p-2
       (i18n "Dashboard")]]
     [:div.cursor-pointer
      [:div.p-2 {:hx-post "/api/logout"}
       (i18n "Logout")]]])])

(defn profile-panel []
  [:a {:href "/model/"}
   [:div.w-96.border.rounded-lg.p-1.text-gray-500
    [:div
     icons/rising-revenue]
    [:div.border-t.mt-2.p-2
     (i18n "Go")]]])

(defcomponent dashboard [req]
  (let []
    [:div.min-h-screen.p-2 {:_ "on click add .hidden to .drop"}
     ;; dropdown
     (main-dropdown)
     [:div.flex.p-3.space-x-4
      (profile-panel)]
     (lang/lang-dropup req)]))
