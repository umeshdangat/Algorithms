function [J, grad] = costFunction(theta, X, y)
%COSTFUNCTION Compute cost and gradient for logistic regression
%   J = COSTFUNCTION(theta, X, y) computes the cost of using theta as the
%   parameter for logistic regression and the gradient of the cost
%   w.r.t. to the parameters.

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta
%
% Note: grad should have the same dimensions as theta
%
%Part 1 : computer JTheta
XTheta = X * theta;     % m * 1 vector
HofX = sigmoid(XTheta); % m * 1 vector
lgofHofX = log(HofX);   % m * 1 vector
costFunc = (y .* lgofHofX) + ((1 .- y) .* (log(1 .- HofX)));  % m * 1 vector
J = -(1/m) *  sum(costFunc);

%Part 2: gradient
HofXMinusY = HofX .- y;    % m * 1 vector
delta = HofXMinusY .* X;   % m * n+1 vector
gradTranspose = (1/m) * sum(delta); % 1 * n+1 vector
grad = gradTranspose' ;    % n+1 * 1vector







% =============================================================

end
