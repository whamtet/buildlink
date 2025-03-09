(ns simpleui.buildlink.web.views.icons)

(defn- hero-icon [& paths]
  [:xmlns.http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg/svg
   {:fill "none",
    :viewBox "0 0 24 24",
    :stroke-width "1.5",
    :stroke "currentColor",
    :class "w-6 h-6"}
   (for [d paths]
     [:xmlns.http%3A%2F%2Fwww.w3.org%2F2000%2Fsvg/path
      {:stroke-linecap "round",
       :stroke-linejoin "round",
       :d d}])])

;; tw-ignore
(defmacro deficon [sym & paths]
  `(def ~sym (hero-icon ~@paths)))
;; tw-ignore

(deficon rising-revenue
  "M3.75 3v11.25A2.25 2.25 0 0 0 6 16.5h2.25M3.75 3h-1.5m1.5 0h16.5m0 0h1.5m-1.5 0v11.25A2.25 2.25 0 0 1 18 16.5h-2.25m-7.5 0h7.5m-7.5 0-1 3m8.5-3 1 3m0 0 .5 1.5m-.5-1.5h-9.5m0 0-.5 1.5m.75-9 3-3 2.148 2.148A12.061 12.061 0 0 1 16.5 7.605")
