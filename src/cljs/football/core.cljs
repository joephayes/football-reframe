(ns football.core
  (:require [reagent.core :as reagent]
            [reagent.dom :as rdom]
            [re-frame.core :as re-frame]
            [football.events]
            [football.subs]
            [football.routes :as routes]
            [football.views :as views]
            [football.config :as config]
            [devtools.core :as devtools]))

(defn dev-setup []
  (when config/debug?
    (devtools/install!)
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (rdom/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (routes/app-routes)
  (re-frame/dispatch-sync [:initialize-db])
  (re-frame/dispatch [:load-from-data-service])
  (dev-setup)
  (mount-root))
