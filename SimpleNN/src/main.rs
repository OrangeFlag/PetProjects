use std::fs::File;
use std::io::{BufReader, BufRead};
use ndarray::{Array2, arr2, ArrayBase, Ix2, OwnedRepr};
use rand::prelude::*;

use plotlib::view::ContinuousView;
use plotlib::line;
use plotlib::page::Page;
use plotlib::style::Line;


struct NeuralNetwork {}

impl NeuralNetwork {
    fn new(activation_function: &Fn(f64) -> f64, derivative_activation_function: &Fn(f64) -> f64) {}

    fn forward(&self) {}
}

fn sigmoid(x: f64) -> f64 {
//    1f64 / (1f64 + std::f64::EPSILON.powf(-x))
    if x > 0f64 {
        x
    } else {
        0f64
    }
}

fn sigmoid_derivative(x: f64) -> f64 {
//    let sig = sigmoid(x);
//    sig * (1f64 - sig)
    if x > 0f64 {
        1f64
    } else {
        0f64
    }
}

fn randomize((_, _): (usize, usize)) -> f64 {
    rand::random()
//    let mut rng = rand::thread_rng();
//    rng.gen_range(-0.5f64, 0.5f64)
}

fn feedforward(input: &ArrayBase<OwnedRepr<f64>, Ix2>, weights: &ArrayBase<OwnedRepr<f64>, Ix2>) -> ArrayBase<OwnedRepr<f64>, Ix2>
{
    let mut layer_res = weights.dot(input);
    for el in layer_res.iter_mut() {
        *el = sigmoid(*el);
    }
    layer_res
}

fn loss(real_output: &ArrayBase<OwnedRepr<f64>, Ix2>, output: &ArrayBase<OwnedRepr<f64>, Ix2>) -> f64 {
    let mut loss = 0f64;
    for (y_r, y) in real_output.iter().zip(output.iter()) {
        loss += (y_r - y).powi(2);
    }
    loss * 0.5f64
}

fn main() {
    let mut weights1 = Array2::<f64>::from_shape_fn((10, 1), randomize);
    let mut weights2 = Array2::<f64>::from_shape_fn((10, 10), randomize);
    let mut weights3 = Array2::<f64>::from_shape_fn((1, 10), randomize);
    let mut output = Array2::<f64>::zeros((1, 1));

    println!("{:?}", weights1);
    println!("{:?}", weights2);
    println!("{:?}", weights3);

    let mut training_data = Vec::with_capacity(1000);
    let file = File::open("cosh-1_-1_+1_data_1000").expect("No such file");
    for line in BufReader::new(file).lines() {
        let line = line.expect("Expect string");
        let numbers: Vec<f64> = line.split_whitespace().map(|x| x.parse::<f64>().expect("Expect f64")).collect();
        training_data.push((numbers[0], numbers[1]))
    }


    for i in 0..100000 {
        let mut error = 0f64;
        let mut prediction_vec = Vec::with_capacity(1000);
        let mut func_vec = Vec::with_capacity(1000);
        for numbers in &training_data {
            let input = arr2(&[[numbers.0]]);
            let real_output = arr2(&[[numbers.1]]);

            let layer1 = feedforward(&input, &weights1);
            let layer2 = feedforward(&layer1, &weights2);
            output = feedforward(&layer2, &weights3);

//            println!("layer1: {:?}", layer1);
//            println!("layer2: {:?}", layer2);
//            println!("output: {:?}", output);

            let E0 = (&output - &real_output).dot(&output.map(|x| sigmoid(*x)));
            let E1 = weights3.t()
                .dot(&E0) * &layer2.map(|x| sigmoid(*x));
            let E2 = weights2.t()
                .dot(&E1) * &layer1.map(|x| sigmoid(*x));


            weights3 -= &E0.dot(&layer2.t()).map(|x| x * 100f64);
            weights2 -= &E1.dot(&layer1.t()).map(|x| x * 100f64);
            weights1 -= &E2.dot(&input.t()).map(|x| x * 100f64);
            prediction_vec.push((input[[0, 0]], output[[0, 0]]));
            func_vec.push((input[[0, 0]], real_output[[0, 0]]));
            error += loss(&real_output, &output);
//            println!("layer1: {:?}", layer1);
//            println!("layer2: {:?}", layer2);
//            println!("output: {:?}", output);
//            println!("weights1: {:?}", weights1);
//            println!("weights2; {:?}", weights2);
//            println!("weights3: {:?}", weights3);
//            println!();
        };
        if i % 100 == 0 {
            let mut cv = ContinuousView::new();
            prediction_vec.sort_by(|a, b| a.0.partial_cmp(&b.0).unwrap());
            func_vec.sort_by(|a, b| a.0.partial_cmp(&b.0).unwrap());
            println!("{:?}", prediction_vec);
            println!("{}", error);
            let mut line_style = line::Style::new();
            let line = line::Line::new(&prediction_vec[..]).style(
                line_style.colour("burlywood").width(2f32)
            );
//            let line2 = line::Line::new(&func_vec[..]).style(
//                line_style.colour("burlywood").width(2f32)
//            );
            cv = cv.add(&line);//.add(&line2);

            Page::single(&cv).save("scatter.svg").unwrap();
        }
    }
}
