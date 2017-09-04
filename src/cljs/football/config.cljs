(ns football.config
  (:require-macros [football.macros :refer [load-key]]))

(def debug?
  ^boolean goog.DEBUG)

(def api-key
  (load-key "football_data.key"))
