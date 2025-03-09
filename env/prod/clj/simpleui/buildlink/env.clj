(ns simpleui.buildlink.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[buildlink starting]=-"))
   :start      (fn []
                 (log/info "\n-=[buildlink started successfully]=-"))
   :stop       (fn []
                 (log/info "\n-=[buildlink has shut down successfully]=-"))
   :middleware (fn [handler _] handler)
   :opts       {:profile :prod}})

(def dev? false)
(def prod? true)
