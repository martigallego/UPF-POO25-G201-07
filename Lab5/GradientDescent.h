#ifndef __GRADIENT_DESCENT__
#define __GRADIENT_DESCENT__

#include "Algorithm.h"

class GradientDescent : public Algorithm {

private:
    int maxIterations;

public:
    GradientDescent(double lr, int maxIter)
      : Algorithm(lr), maxIterations(maxIter) {
    }
    
    Vector solve(const Dataset & data, Model & model) override {
        std::vector<Record> records = data.getData();
        int n = records.size();
        int dim = data.getDim();
        
        for (int iter = 0; iter < maxIterations; ++iter) {
            Vector gradient(dim + 1, 0);
            
            // Calcular el gradient per tots els records
            for (int i = 0; i < n; ++i) {
                Vector input = records[i].getInput().augment();
                double output = records[i].getOutput();
                double prediction = model.predict(input);
                double error = prediction - output;
                
                // Acumular gradient
                Vector grad = input.multiply(error);
                gradient = gradient.add(grad);
            }
            
            // Gradient mitjà
            gradient = gradient.divide(n);
            
            // Actualitzar model
            model.update(gradient, learningRate);
        }
        
        // Retornar un vector de losses (opcional, podem retornar vector buit)
        return Vector(1, 0);
    }
};

#endif