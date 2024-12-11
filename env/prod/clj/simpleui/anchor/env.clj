(ns simpleui.anchor.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[anchor starting]=-"))
   :start      (fn []
                 (log/info "\n-=[anchor started successfully]=-"))
   :stop       (fn []
                 (log/info "\n-=[anchor has shut down successfully]=-"))
   :middleware (fn [handler _] handler)
   :opts       {:profile :prod}})
