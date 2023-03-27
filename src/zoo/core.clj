(ns zoo.core
  (:require [clojure.string :refer [upper-case]]))

(def ^:private species-keywords
  {"dog" :dog
   "cat" :cat
   "mouse" :mouse})

(def ^:private calls
  {:dog "woof"
   :cat "meow"
   :mouse "eek"})

(def ^:private satisfaction-levels
  {:dog 35
   :cat 25
   :mouse 10})

(def ^:private day-keywords
  {"monday" :monday
   "tuesday" :tuesday
   "wednesday" :wednesday
   "thursday" :thursday
   "friday" :friday
   "saturday" :saturday
   "sunday" :sunday})

(def ^:private feed-levels
  {:monday 40
   :tuesday 20
   :wednesday 40
   :thursday 20
   :friday 40
   :saturday 40
   :sunday 40})

(def ^:private zoo
  (atom (sorted-map)))

(defn- make-animal
  "Animals are represented as maps of keys: :species, :name, :call and
   :satisfaction-level. Given SPECIES and NAME, return a new animal."
  [species name]
  (let [species-key (species-keywords species)]
    {:species species-key
     :name name
     :call (calls species-key)
     :satisfaction-level (satisfaction-levels species-key)}))

(defn- feed
  "Feed ANIMAL an AMOUNT of food. Returns the animal's call if it is satisfied,
   it's call in upper-case if it is not!"
  [animal amount]
  (let [call (animal :call)]
    (if (<= amount (animal :satisfaction-level))
      call
      (upper-case call))))

(defn add-animal
  "Add an animal called NAME of SPECIES to the zoo. Returns the value of zoo
   if the animal was successfully added, FALSE otherwise (an animal with NAME
   already exists)."
  [species name]
  (letfn [(add [zoo]
            (if (not (contains? zoo name))
              (assoc zoo name (make-animal species name))
              (throw (IllegalStateException. "name already exists in zoo"))))]
    (try
      (swap! zoo add)
      (catch Exception _ex
        false))))

(defn feed-aminals
  "Feed all the animals in the zoo on DAY."
  [day]
  (let [day-key (day-keywords day)]
    (map (fn [animal]
           (feed animal (feed-levels day-key)))
         (vals @zoo))))

;; Tests.

(assert (add-animal "cat" "felix")
        "felix the cat added to the zoo")

(assert (not (add-animal "cat" "felix"))
        "felix the cat not added to the zoo")

(assert (add-animal "cat" "tom")
        "tom the cat added to the zoo")

(assert (add-animal "mouse" "jerry")
        "jerry the mouse added to the zoo")

(assert (add-animal "dog" "fido")
        "fido the dog added to the zoo")

(assert (= ["MEOW" "WOOF" "EEK" "MEOW"]
           (feed-aminals "monday")))

(assert (= ["meow" "woof" "EEK" "meow"]
           (feed-aminals "tuesday")))