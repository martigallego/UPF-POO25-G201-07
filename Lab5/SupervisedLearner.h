#ifndef __SUPERVISED_LEARNER__
#define __SUPERVISED_LEARNER__

#include "Algorithm.h"
#include "Model.h"
#include "Dataset.h"

class SupervisedLearner {

private:
    Algorithm * algorithm;
    Model * model;

public:
    SupervisedLearner(Algorithm * algo, int inputDim)
      : algorithm(algo), model(new Model(inputDim + 1)) {
    }
    
    ~SupervisedLearner() {
        delete model;
    }
    
    void train(const Dataset & data) {
        algorithm->solve(data, *model);
    }
    
    double predict(const Vector & input) const {
        Vector augmented = input.augment();
        return model->predict(augmented);
    }
    
    double evaluate(const Dataset & data) const {
        std::vector<Record> records = data.getData();
        double totalError = 0;
        
        for (size_t i = 0; i < records.size(); ++i) {
            Vector input = records[i].getInput();
            double expected = records[i].getOutput();
            double prediction = predict(input);
            double error = prediction - expected;
            totalError += error * error;
        }
        
        return totalError / records.size();
    }
    
    friend std::ostream & operator<<(std::ostream & os, SupervisedLearner & sl) {
        return os << *sl.model;
    }
};

#endif