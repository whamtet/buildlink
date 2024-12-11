(ns simpleui.anchor.web.views.home
    (:require
      [simpleui.anchor.web.views.login :as login]
      [simpleui.core :as simpleui :refer [defcomponent]]
      [simpleui.anchor.web.htmx :refer [page-htmx]]))

(defn ui-routes [{:keys [query-fn]}]
  (simpleui/make-routes
   ""
   [query-fn]
   (fn [req]
     (let [req (assoc req :query-fn query-fn)]
       (page-htmx
        {:css ["/output.css"] :hyperscript? true}
        (if (some->> req :session :id)
          "todo"
          (login/login req)))))))
