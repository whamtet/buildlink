(ns simpleui.anchor.web.controllers.login
  (:require
    simpleui.response))

(defn- refresh-session [session]
  (assoc simpleui.response/hx-refresh :session session))

(defn- assoc-session [session & keys]
  (refresh-session
   (apply assoc session keys)))

(defn- dissoc-session [session & keys]
  (refresh-session
   (apply dissoc session keys)))

(defn assoc-lang [session lang]
  (assoc-session session :lang lang))

(defn login [{:keys [session]}
             email
             password]
  (if true
    (assoc-session session :id 1)
    :unknown))

(defn logout [session]
  (dissoc-session session :id))
