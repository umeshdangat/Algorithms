function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

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

XTheta = X * theta;     % m * 1 vector
HofX = sigmoid(XTheta); % m * 1 vector
lgofHofX = log(HofX);   % m * 1 vector
costFunc = (y .* lgofHofX) + ((1 .- y) .* (log(1 .- HofX)));  % m * 1 vector
costFuncSummationTerm = -(1/m) *  sum(costFunc) ;
regularizationTerm    = (lambda/(2*m)) * sum(theta(2:length(theta)) .^ 2);
J = costFuncSummationTerm + regularizationTerm;

%Part 2: gradient
HofXMinusY = HofX .- y;    % m * 1 vector
delta = HofXMinusY .* X(:,2:size(X,2));   % m * n vector, not first column of X, i.e. x0 terms
gradDescentCostSummationTerm = (1/m) * sum(delta); % 1 * n vector
gradDescentCostSummationTermTranspose = gradDescentCostSummationTerm'; % n * 1 vector
gradDescentRegularizationTerm = (lambda/m) * theta(2:length(theta));  % n * 1 vector
grad(1) = (1/m) * sum(HofXMinusY);
grad(2:size(theta,1)) = gradDescentCostSummationTermTranspose +  gradDescentRegularizationTerm;    % n * 1vector




% =============================================================

end
