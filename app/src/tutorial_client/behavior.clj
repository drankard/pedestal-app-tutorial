(ns ^:shared tutorial-client.behavior
    (:require [clojure.string :as string]
              [io.pedestal.app.messages :as msg]
              [io.pedestal.app :as app]))


;; Transformers

(defn swap-transform [_ message]
  (:value message))


(defn inc-transform [old-value _]
  ((fnil inc 0) old-value))


(defn init-main [_] ;; en emitter
  [[:transform-enable [:main :my-counter] :inc [{msg/topic [:my-counter]}]]])


;; Effects

(defn publish-counter [count]
  [{msg/type :swap msg/topic [:other-counters] :value count}])


;; Derives

(defn total-count [_ nums] (apply + nums))

(defn maximum [old-value nums]
  (apply max (or old-value 0) nums))

(defn average-count [_ {:keys [total nums]}]
  (/ total (count nums)))

(defn merge-counters [_ {:keys [me others]}]
  (assoc others "Me" me))

(def example-app
  {:version 2
   :transform [[:inc [:my-counter] inc-transform]
               [:swap [:**] swap-transform]]
   :effect #{[#{[:my-counter]} publish-counter :single-val]}

   :derive #{[{[:my-counter] :me [:other-counters] :others} [:counters] merge-counters :map]
            [#{[:counters :*]} [:total-count] total-count :vals]
            [#{[:counters :*]} [:max-count] maximum :vals]
            [{[:counters :*] :nums [:total-count] :total} [:average-count] average-count :map]}

     :emit [{:init init-main}
          [#{[:my-counter]
   [:other-counters :*]
   [:total-count]
   [:max-count]
   [:average-count]} (app/default-emitter [:main])]
            ]

   })

