from random import random, choice
from math import e


class Neuron(object):

    def __init__(self, num_inputs):
        self.inputs = []
        self.learning_rate = 0.01
        self.weights = [random() for _ in range(num_inputs)]
        self.bias = random()

    @staticmethod
    def activation(x):
        """ReLU function"""
        return (x > 0) * x
        # return 1 / (1 + e ** (-x))

    @staticmethod
    def sensitivity(x):
        """Derivative of ReLU"""
        # sig = Neuron.activation(x)
        return (x > 0) * 1
        # return sig * (1 - sig)

    def output(self):
        c = sum([w * i for w, i in zip(self.weights, self.inputs)])
        return self.activation(self.bias + c)

    def adjust(self, error):
        correction = self.sensitivity(self.output())
        correction *= self.learning_rate * error
        self.weights = [w + correction * i
                        for w, i in zip(self.weights, self.inputs)]
        self.bias += correction


class NeuralNetwork(object):

    def __init__(self, inputs=2, hidden_neurons=2):
        self.hidden = [Neuron(inputs) for _ in range(hidden_neurons)]
        self.y = Neuron(hidden_neurons)

    def predict(self, input):
        for h in self.hidden:
            h.inputs = input

        self.y.inputs = [h.output() for h in self.hidden]

        return self.y.output()

    def learn(self, input, target):
        error = target - self.predict(input)
        self.y.adjust(error)

        for h, w in zip(self.hidden, self.y.weights):
            h.adjust(error * w)


if __name__ == "__main__":
    nn = NeuralNetwork(inputs=1, hidden_neurons=40)
    training = []
    f = open("cosh_data_1000", "r")
    for line in f.readlines():
        training.append([float(x) for x in line.split(" ")])

    for epoch in range(1000000):
        x, y = choice(training)
        nn.learn([x], y)

    for input, target in training:
        print('IN: {}, EXPECTED: {}, RESULT: {:.2f}'
              .format(input, target, nn.predict([input])))

