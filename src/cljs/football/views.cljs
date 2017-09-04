(ns football.views
  (:require [re-frame.core :as re-frame]
            [cljs.pprint :refer [pprint]]))


;; home

(defn error-message []
  (let [error-result (re-frame/subscribe [:error-result])]
    (fn []
      [:pre (with-out-str (pprint @error-result))])))

(defn fixtures []
  (let [response-data (re-frame/subscribe [:response-data])]
    (fn []
      [:pre (with-out-str (pprint @response-data))])))

(defn home-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div (str "Hello from " @name ". This is the Home Page.")
       [:div [:a {:href "#/about"} "go to About Page"]]
      [ error-message ]
      [ fixtures ]])))

;; about

(defn about-panel []
  (fn []
    [:div "This is the About Page."
     [:div [:a {:href "#/"} "go to Home Page"]]]))


;; main

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home-panel]
    :about-panel [about-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
