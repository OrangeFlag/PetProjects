use rand::prelude::*;

struct TargetFunc<F>
    where F: Fn(f64) -> f64
{
    f: F,
    start: f64,
    end: f64,
}

impl<F> TargetFunc<F>
    where F: Fn(f64) -> f64
{
    fn new(f: F, start: f64, end: f64) -> Self {
        TargetFunc { f, start, end }
    }

    fn get_random(&self) -> (f64, f64) {
        let mut rng = rand::thread_rng();
        let x = rng.gen_range(self.start, self.end);
        (x, (self.f)(x))
    }
}

fn main() {
    let func = &TargetFunc::new(|x: f64| { x.cosh().powi(2) - 1f64 }, -1f64, 1f64);
    let count_measurements = 1000;
    for _ in 0..count_measurements {
        let (x, y) = func.get_random();
        println!("{} {}", x, y)
    }
}
