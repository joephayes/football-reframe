(ns football.events
  (:require [re-frame.core :as re-frame]
            [football.config :as config]
            [football.db :as db]
            [ajax.core :as ajax]
            [day8.re-frame.http-fx]))

(re-frame/reg-event-db
  :initialize-db
  (fn
    [_ _]
    db/default-db))

(re-frame/reg-event-db
  :set-active-panel
  (fn
    [db [_ active-panel]]
    (assoc db :active-panel active-panel)))

(re-frame/reg-event-fx
  :load-from-data-service
  (fn
    [{db :db} _]
    {:db (assoc db :loading? true)
     :http-xhrio {:method :get
                  :uri "http://api.football-data.org/v1/fixtures"
                  :headers {"X-Auth-Token" config/api-key}
                  :timeout 2000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:process-response]
                  :on-failure [:bad-response]}}))

(re-frame/reg-event-db
  :process-response
  (fn
    [db [_ response]]
    (-> db
        (assoc :loading? false)
        (assoc :response-data (js->clj response)))))

(re-frame/reg-event-db
  :bad-response
  (fn
    [db [_ result]]
    (-> db
        (assoc :loading? false)
        (assoc :error-result result))))
