(ns football.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :active-panel
 (fn [db _]
   (:active-panel db)))

(re-frame/reg-sub
  :error-result
  (fn [db]
    (:error-result db)))

(re-frame/reg-sub
  :response-data
  (fn [db]
    (:response-data db)))

(re-frame/reg-sub
  :initialised?
  (fn [db _]
    (not (empty? db))
    (:data-service-answered? db)))
