(ns simpleui.buildlink.env
  (:require
    [clojure.tools.logging :as log]
    [simpleui.buildlink.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[buildlink starting using the development or test profile]=-"))
   :start      (fn []
                 (log/info "\n-=[buildlink started successfully using the development or test profile]=-"))
   :stop       (fn []
                 (log/info "\n-=[buildlink has shut down successfully]=-"))
   :middleware wrap-dev
   :opts       {:profile       :dev
                :persist-data? true}})

(def dev? true)
(def prod? false)
