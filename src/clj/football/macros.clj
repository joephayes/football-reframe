(ns football.macros)

;; from https://github.com/voldmar/cljs-yandex-asr/blob/master/src/cljs_yandex_asr/core.clj
(defmacro load-key
  "Load key from any slurpable source"
  [path]
  (clojure.string/trim (slurp path)))
