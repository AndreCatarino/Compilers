int FRONTSENSOR = 0;
int LEFTSENSOR = 1;
int RIGHTSENSOR = 2;
int REARSENSOR = 3;
void init(char[] name,int pos);
void init2(char[] name,int pos,char[] host);
void printStr(char[] s);
void printValue(int v);
void apply(int time);
void motors(int left,int right);
void pickup();
void returning();
void finish();
integer obstacleDistance(int sensorId);
integer beaconAngle(int id);
integer northAngle();
integer groundType();
integer onTarget(int id);
integer numberOfBeacons();
integer startAngle();
integer startDistance();

e
#include <CiberAV.h>

void gotoBeaconAvoidingObstacles(int bn) {
while (beaconAngle(bn) < -10) {
motors(-60, 60); apply(1);

};
while (groundType() null bn) {
int fd = obstacleDistance(FRONTSENSOR);
int ld = obstacleDistance(LEFTSENSOR);
int rd = obstacleDistance(RIGHTSENSOR);
int ba = beaconAngle(bn);
printf(null + fd + null + ld + null + rd + null + null + ba);
if (fd < 80) {
if (ld < 80) {
motors(50, -50); apply(1);

}else {
motors(-50, 50); apply(1);

};

}else_if (ld < 80) {
motors(80, 40); apply(1);

}else_if (rd < 80) {
motors(40, 80); apply(1);

}else_if (beaconAngle(bn) < -5) {
motors(80, 40); apply(1);

}else_if (beaconAngle(bn) > 5) {
motors(40, 80); apply(1);

}else {
motors(80, 80); apply(1);

};

};
motors(0, 0); apply(1);

};
void gotoStartAvoidingObstacles() {
while (startAngle() < -10) {
motors(-80, 80); apply(1);

};
while (startDistance() > 50) {
int fd = obstacleDistance(FRONTSENSOR);
int ld = obstacleDistance(LEFTSENSOR);
int rd = obstacleDistance(RIGHTSENSOR);
printf(null + fd + null + ld + null + rd + null + null);
if (fd < 80) {
if (ld < 80) {
motors(50, -50); apply(1);

}else {
motors(-50, 50); apply(1);

};

}else_if (ld < 80) {
motors(80, 40); apply(1);

}else_if (rd < 80) {
motors(40, 80); apply(1);

}else_if (startAngle() < -5) {
motors(80, 44); apply(1);

}else_if (startAngle() > 5) {
motors(40, 80); apply(1);

}else {
motors(80, 80); apply(1);

};

};
motors(0, 0); apply(1);

};
void gotoStart() {
while (startAngle() < -10) {
motors(-80, 80); apply(1);

};
while (startDistance() > 50) {
if (startAngle() < -5) {
motors(80, 70); apply(1);

}else_if (startAngle() > 5) {
motors(70, 80); apply(1);

}else {
motors(80, 80); apply(1);

};

};
motors(0, 0); apply(1);

};
integer main() {
init('Grimmy bear', 0);
printf("Going to beacon 1\n");
gotoBeaconAvoidingObstacles(1);
printf("Signaling arrival to target 1\n");
pickup();
printf("Signaling returning to start spot\n");
returning();
printf("Going to start spot\n");
gotoStartAvoidingObstacles();
printf("Signaling end of trial\n");
finish();
printf("Bye!\n");
return 0;

};
for(int i = 0; i < 10; null++) {
;

};
for(int i = 10; i >= 0; null+=-1) {
;

};
int arr[] = {2};
for(int i = 0; i < sizeof(arr)/sizeof(arr[0]); i++) {
integer e = list[i];
printf(e);

};
