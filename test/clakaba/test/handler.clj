(ns clakaba.test.handler
  (:use clojure.test
        ring.mock.request
        clakaba.handler))

(deftest test-app
  (testing "Main Route"
    (let [response (app (request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hi dude!"))))

  (testing "404 Route"
    (let [response (app (request :get "/invalid"))]
      (is (= (:status response) 404)))))
