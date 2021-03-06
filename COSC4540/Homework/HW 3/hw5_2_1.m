% Liam Fruzyna
% MATH 4540
% Assignment 3

format long;

% 5.2 1c) Use the composite trapezoid rule with m = 16 and m = 32 panels to
% approximate the definite integral of xe^x from 0 to 1

f = @(x) x * exp(x);

syms x;
definite = int(f(x), [0 1])

approx = trapezoid(f, 16, 0, 1)
error = double(abs(definite - approx))

approx = trapezoid(f, 32, 0, 1)
error = double(abs(definite - approx))

function[result] = trapezoid(f, m, x0, x1)
    result = 0;
    h = (x1 - x0) / m;
    for i=1:m
        x1 = x0 + h;
        y0 = f(x0);
        y1 = f(x1);
        result = result + (h / 2) * (y0 + y1);
        x0 = x1;
    end
end