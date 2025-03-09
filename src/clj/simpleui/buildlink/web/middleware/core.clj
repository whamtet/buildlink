(ns simpleui.buildlink.web.middleware.core
  (:require
    [simpleui.buildlink.env :as env]
    [simpleui.buildlink.web.middleware.i18n :as i18n]
    [ring.middleware.defaults :as defaults]
    [ring.middleware.session.cookie :as cookie]))

(defn wrap-base
  [{:keys [metrics site-defaults-config cookie-secret] :as opts}]
  (let [cookie-store (cookie/cookie-store {:key (.getBytes ^String cookie-secret)})]
    (fn [handler]
      (cond-> ((:middleware env/defaults) handler opts)
              true i18n/set-lang
              true (defaults/wrap-defaults
                     (assoc-in site-defaults-config [:session :store] cookie-store))
              ))))
