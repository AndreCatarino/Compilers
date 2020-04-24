#include "CiberAV.h"

void gotoBeaconAvoidingObstacles(int bn) {
while (beaconAngle(bn) < -10) {
motors(-60, 60); apply(1);

};
while (groundType() != bn) {
int fd = obstacleDistance(FRONTSENSOR);
int ld = obstacleDistance(LEFTSENSOR);
int rd = obstacleDistance(RIGHTSENSOR);
int ba = beaconAngle(bn);
printf("%s\n", "distances: ");
printf("%d\n", fd);
printf("%s\n", ", ");
printf("%d\n", ld);
printf("%s\n", ", ");
printf("%d\n", rd);
printf("%s\n", "; ");
printf("%s\n", "angle: ");
printf("%d\n", ba);
printf("%s\n", "\n");
if (fd < 80) {
if (ld < 80) {
motors(50, -50); apply(1);

}else {
motors(-50, 50); apply(1);

};

}else if (ld < 80) {
motors(80, 40); apply(1);

}else if (rd < 80) {
motors(40, 80); apply(1);

}else if (beaconAngle(bn) < -5) {
motors(80, 40); apply(1);

}else if (beaconAngle(bn) > 5) {
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
printf("%s\n", "distances: ");
printf("%d\n", fd);
printf("%s\n", ", ");
printf("%d\n", ld);
printf("%s\n", ", ");
printf("%d\n", rd);
printf("%s\n", "\n");
if (fd < 80) {
if (ld < 80) {
motors(50, -50); apply(1);

}else {
motors(-50, 50); apply(1);

};

}else if (ld < 80) {
motors(80, 40); apply(1);

}else if (rd < 80) {
motors(40, 80); apply(1);

}else if (startAngle() < -5) {
motors(80, 44); apply(1);

}else if (startAngle() > 5) {
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

}else if (startAngle() > 5) {
motors(70, 80); apply(1);

}else {
motors(80, 80); apply(1);

};

};
motors(0, 0); apply(1);

};
int main() {
init("Grimmy bear", 0);
printf("%s\n", "Going to beacon 1\n");
gotoBeaconAvoidingObstacles(1);
printf("%s\n", "Signaling arrival to target 1\n");
pickup();
printf("%s\n", "Signaling returning to start spot\n");
returning();
printf("%s\n", "Going to start spot\n");
gotoStartAvoidingObstacles();
printf("%s\n", "Signaling end of trial\n");
finish();
for(int i = 0; i < 10; i++) {
break;

};
for(int i = 10; i >= 0; i+=-1) {
break;

};
int arr[] = {1, 1, 2};
for(int i = 0; i < sizeof(arr)/sizeof(arr[0]); i++) {
int e = arr[i];
printf("%d\n", e);

};
printf("%s\n", "Bye!\n");
return 0;

};

