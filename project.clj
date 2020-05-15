(defproject football "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojurescript "1.10.764"]
                 [org.clojure/clojure "1.10.1"]
                 [reagent "0.10.0"]
                 [re-frame "0.12.0"]
                 [secretary "1.2.3"]
                 [cljs-ajax "0.8.0"]
                 [day8.re-frame/http-fx "0.1.6"]]

  :plugins [[lein-cljsbuild "1.1.8"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {:css-dirs ["resources/public/css"]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "1.0.0"]]

    :plugins      [[lein-figwheel "0.5.20"]]}}


  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "football.core/mount-root"}
     :compiler     {:main                 football.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}}}


    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            football.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]})





