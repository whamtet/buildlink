(ns simpleui.buildlink.web.views.home
    (:require
      [simpleui.buildlink.web.views.dashboard :as dashboard]
      [simpleui.buildlink.web.views.login :as login]
      [simpleui.core :as simpleui :refer [defcomponent]]
      [simpleui.buildlink.web.htmx :refer [page-htmx]]))

(defn ui-routes [{:keys [query-fn]}]
  (simpleui/make-routes
   ""
   [query-fn]
   (fn [req]
     (let [req (assoc req :query-fn query-fn)]
       (page-htmx
        {:css ["/output.css"] :hyperscript? true}
        (if (some->> req :session :id)
          (dashboard/dashboard req)
          (login/login req)))))))
