function [] = distVisual()
nxy=5;
%xy = [0,-3,5,5,-5,-5;0,3,5,-5,-5,5];
%vals=[0,5,5,5,5];
%noisyvals = [0,6,7,5,3,4];
xy = [0,0,3,9,8,4,8,4,0,-3,-6,-7,-8,-6,-9;0,8,6,9,0,-2,-5,-8,-4,-2,-7,0,3,5,8];
vals=[0,8,6.7,12.7,8,4.5,9.5,9,6,3.6,9.2,7,8.5,7.8,12];
noisyvals2 = [0,5,5,14,12,3,15,8,6,5,7,8,11,10,10];
noisyvals = noisyvals2./vals;
st = tpaps(xy,noisyvals,1); 
fnplt(st), hold on
avals = fnval(st,xy);
plot3(xy(1,:),xy(2,:),noisyvals,'wo','markerfacecolor','k')
%quiver3(xy(1,:),xy(2,:),avals,zeros(1,nxy),zeros(1,nxy), ...
%         noisyvals-avals,'r'), hold off