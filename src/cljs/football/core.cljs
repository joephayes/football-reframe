(ns football.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [football.events]
            [football.subs]
            [football.routes :as routes]
            [football.views :as views]
            [football.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
