(ns simpleui.anchor.env
  (:require
    [clojure.tools.logging :as log]
    [simpleui.anchor.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[anchor starting using the development or test profile]=-"))
   :start      (fn []
                 (log/info "\n-=[anchor started successfully using the development or test profile]=-"))
   :stop       (fn []
                 (log/info "\n-=[anchor has shut down successfully]=-"))
   :middleware wrap-dev
   :opts       {:profile       :dev
                :persist-data? true}})

(def dev? true)
(def prod? false)
