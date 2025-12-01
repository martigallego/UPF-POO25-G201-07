#ifndef __STOCHASTIC_GRADIENT_DESCENT__
#define __STOCHASTIC_GRADIENT_DESCENT__

#include "Algorithm.h"
#include <random>
#include <algorithm>

class StochasticGradientDescent : public Algorithm {

private:
    int maxIterations;
    int batchSize;

public:
    StochasticGradientDescent(double lr, int maxIter, int batch)
      : Algorithm(lr), maxIterations(maxIter), batchSize(batch) {
    }
    
    Vector solve(const Dataset & data, Model & model) override {
        std::vector<Record> records = data.getData();
        int dim = data.getDim();
        int n = records.size();
        
        std::random_device rd;
        std::mt19937 gen(rd());
        
        for (int iter = 0; iter < maxIterations; ++iter) {
            // Crear batch aleatori
            std::vector<Record> batch;
            std::vector<int> indices(n);
            for (int i = 0; i < n; ++i) {
                indices[i] = i;
            }
            std::shuffle(indices.begin(), indices.end(), gen);
            
            // Agafar els primers batchSize elements
            int actualBatchSize = (batchSize < n) ? batchSize : n;
            for (int i = 0; i < actualBatchSize; ++i) {
                batch.push_back(records[indices[i]]);
            }
            
            Vector gradient(dim + 1, 0);
            
            // Calcular el gradient pel batch
            for (size_t i = 0; i < batch.size(); ++i) {
                Vector input = batch[i].getInput().augment();
                double output = batch[i].getOutput();
                double prediction = model.predict(input);
                double error = prediction - output;
                
                // Acumular gradient
                Vector grad = input.multiply(error);
                gradient = gradient.add(grad);
            }
            
            // Gradient mitjà del batch
            gradient = gradient.divide(batch.size());
            
            // Actualitzar model
            model.update(gradient, learningRate);
        }
        
        // Retornar un vector de losses (opcional)
        return Vector(1, 0);
    }

};

#endif