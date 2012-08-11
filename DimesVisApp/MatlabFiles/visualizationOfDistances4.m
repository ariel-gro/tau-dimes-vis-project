function [] = visualizationOfDistances4(filepath)

%fid=fopen('C:/javaTimesfile.txt');
fid=fopen(filepath);

n=fscanf(fid,'%f',1);
source_lat=fscanf(fid,'%f',1);
source_lon=fscanf(fid,'%f',1);
strings=fscanf(fid,'%s',n);
strings=regexp(strings,',','split');
names=fscanf(fid,'%s',n);
names=regexp(names,',','split');
x=fscanf(fid,'%f',n);
x=x.';

y=fscanf(fid,'%f',n);
y=y.';

times=fscanf(fid,'%f',n);
times=times.';
virtual_distance=times.*100;
viewoption=fscanf(fid,'%f',1);
displayText=fscanf(fid,'%f',1);
fclose(fid);

target_lat=deg2rad(x);
target_lon=deg2rad(y);
R=6356+21.3*(sin(source_lat)+sin(target_lat))/2;

d_lat=sin((target_lat-source_lat)/2).^2;
d_lon=sin((target_lon-source_lon)/2).^2;
distance=2*R.*asin(sqrt(d_lat+cos(source_lat)*cos(target_lat).*d_lon));

distance_ratio = virtual_distance./distance;
distance_ratio = [1,distance_ratio];

x=[source_lat,x];
y=[source_lon,y];
xy=[x;y];

st = tpaps(xy,distance_ratio,0.5); 
strings=[' ',strings];
strings=strcat('\bf\color{magenta}',strings);
str='S )   - source';
for j=1:n,
   temp=strcat(num2str(j),' )   -',names(j));
   str=[str;temp];
end
%helpdlg(str,'Points Legend');

avals = fnval(st,xy);

if (viewoption==4)
figure('Position',[50,60,850,760]);    
subplot(2,2,1)
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.5);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',12,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',14,'HorizontalAlignment','center');
end
view([30 60]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')

subplot(2,2,2)
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.5);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',12,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',14,'HorizontalAlignment','center');
end
view([0 90]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')

subplot(2,2,3)
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.5);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',12,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',14,'HorizontalAlignment','center');
end
view([330 30]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')

subplot(2,2,4)
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.5);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',12,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',14,'HorizontalAlignment','center');
end
view([270 0]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')

elseif (viewoption==0)
figure('Position',[50,80,700,600]);
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.75);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',16,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',20,'HorizontalAlignment','center');
end
view([30 60]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')

elseif (viewoption==2)
figure('Position',[50,80,700,600]);
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.7);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',16,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',20,'HorizontalAlignment','center');
end
view([0 90]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')

elseif (viewoption==3)
figure('Position',[50,80,700,600]);
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.5);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',16,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',20,'HorizontalAlignment','center');
end
view([330 30]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')  

elseif (viewoption==1)
figure('Position',[50,80,700,600]);
fnplt(st), hold on
plot3(xy(1,:),xy(2,:),distance_ratio,'wo','markerfacecolor','k');
alpha(0.5);
plot3(source_lat,source_lon,1,'wo','markerfacecolor','r');
if (displayText==1)
text(xy(1,:),xy(2,:),distance_ratio(:)+0.5,strings(:),'rotation',0,'FontSize',16,'HorizontalAlignment','center');
text(source_lat,source_lon,2,'\bf\color{red} S','rotation',0,'FontSize',20,'HorizontalAlignment','center');
end
view([270 0]);
deltaX=0.1*(max(x)-min(x));
deltaY=0.1*(max(y)-min(y));
axis([min(x)-deltaX max(x)+deltaX min(y)-deltaY max(y)+deltaY])
xlabel('Lat') 
ylabel('Lan')
title('dist ratio, virtual/geographic',... 
  'FontWeight','bold')    
    
end

