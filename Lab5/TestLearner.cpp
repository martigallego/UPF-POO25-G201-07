#include "Dataset.h"
#include "Model.h"
#include "GradientDescent.h"
#include "StochasticGradientDescent.h"
#include "SupervisedLearner.h"

int main() {

    // Test dels vectors (codi original)
    Vector v1(std::vector<double> { 0.5, 1.5, 2.5 });
    Vector v2(std::vector<double> { 8.0, 6.0, 4.0 });
    Vector v3(std::vector<double> { 3.6, 4.8, 7.2 });
    
    std::cout << "=== Test Vectors ===" << "\n";
    std::cout << v1 << "\n" << v2 << "\n" << v3 << "\n";
    
    Vector v4 = v1.add(v3);
    Vector v5 = v2.subtract(v1);
    Vector v6 = v1.multiply(4);
    Vector v7 = v3.divide(1.2);
    Vector v8 = v2.multiply(v2);
    
    std::cout << v4 << "\n" << v5 << "\n" << v6 << "\n" << v7 << "\n" << v8 << "\n";
    
    double d1 = v1.dotProduct(v2);
    double d2 = v2.norm();
    
    std::cout << d1 << " " << d2 << "\n\n";
    
    // Test de regressió lineal
    std::cout << "=== Test Linear Regression ===" << "\n";
    
    // Crear un dataset simple: y = 2*x1 + 3*x2 + 1
    Dataset trainingData(2);
    trainingData.addRecord(Record(Vector(std::vector<double>{1.0, 2.0}), 9.0));
    trainingData.addRecord(Record(Vector(std::vector<double>{2.0, 1.0}), 8.0));
    trainingData.addRecord(Record(Vector(std::vector<double>{3.0, 3.0}), 16.0));
    trainingData.addRecord(Record(Vector(std::vector<double>{0.0, 1.0}), 4.0));
    trainingData.addRecord(Record(Vector(std::vector<double>{4.0, 2.0}), 15.0));
    
    std::cout << "Training data: " << trainingData << "\n\n";
    
    // Test amb Gradient Descent
    std::cout << "--- Gradient Descent ---" << "\n";
    GradientDescent gd(0.01, 1000);
    SupervisedLearner learner1(&gd, 2);
    
    std::cout << "Model inicial: " << learner1 << "\n";
    learner1.train(trainingData);
    std::cout << "Model entrenat: " << learner1 << "\n";
    std::cout << "Error (MSE): " << learner1.evaluate(trainingData) << "\n";
    
    // Fer prediccions
    Vector testInput(std::vector<double>{1.5, 2.5});
    std::cout << "Predicció per " << testInput << ": " 
              << learner1.predict(testInput) << "\n\n";
    
    // Test amb Stochastic Gradient Descent
    std::cout << "--- Stochastic Gradient Descent ---" << "\n";
    StochasticGradientDescent sgd(0.01, 2000, 2);
    SupervisedLearner learner2(&sgd, 2);
    
    std::cout << "Model inicial: " << learner2 << "\n";
    learner2.train(trainingData);
    std::cout << "Model entrenat: " << learner2 << "\n";
    std::cout << "Error (MSE): " << learner2.evaluate(trainingData) << "\n";
    
    // Fer prediccions
    std::cout << "Predicció per " << testInput << ": " 
              << learner2.predict(testInput) << "\n";
    
    return 0;
}