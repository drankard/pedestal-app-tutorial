{:config {:order 0, :description "Tester", :name :tutorial}
 :data
 [
  [:node-create [] :map]
  [:node-create [:main] :map]
  [:node-create [:main :my-counter] :map]
  [:value [:main :my-counter] nil 1]
  [:transform-enable [:main :my-counter] :inc [{:io.pedestal.app.messages/topic [:my-counter]}]]
  [:node-create [:main :total-count] :map]
  [:value [:main :total-count] nil 4]
  [:node-create [:main :average-count] :map]
  [:value [:main :average-count] nil 1.3333333333333333]
  [:node-create [:main :max-count] :map]
  [:value [:main :max-count] nil 2]
  [:node-create [:main :other-counters] :map]
  [:node-create [:main :other-counters "abc"] :map]
  [:value [:main :other-counters "abc"] nil 2]
  [:node-create [:main :other-counters "xyz"] :map]
  [:value [:main :other-counters "xyz"] nil 1]
  :break
  [:value [:main :other-counters "abc"] 2 3]
  [:value [:main :total-count] 4 5]
  [:value [:main :average-count] 1.3333333333333333 1.6666666666666667]
  [:value [:main :max-count] 2 3]
  :break
  [:value [:main :other-counters "xyz"] 1 2]
  [:value [:main :total-count] 5 6]
  [:value [:main :average-count] 1.6666666666666667 2]
  :break
  [:value [:main :total-count] 6 7]
  [:value [:main :average-count] 2 2.3333333333333335]
  [:value [:main :my-counter] 1 2]
  :break
  [:value [:main :other-counters "abc"] 3 4]
  [:value [:main :total-count] 7 8]
  [:value [:main :average-count] 2.3333333333333335 2.6666666666666665]
  [:value [:main :max-count] 3 4]
  :break
  [:value [:main :total-count] 8 9]
  [:value [:main :average-count] 2.6666666666666665 3]
  [:value [:main :my-counter] 2 3]
 ]}