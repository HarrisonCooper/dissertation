import matplotlib.pyplot as plt

iteration = [1,2,3,4,5,6,7,8,9]
iteration1 = [1,2,3,4,5,6,7,8]
iteration2 = [1,2,3,4,5,6,7]
iteration3 = [1,2,3,4,5,6]
iteration4 = [1,2,3,4,5]

num2025 = [
58.6,
76,
92,
106,
128,
155.2,
175,
190,
182
]
error2025 = [
1.687601849,
3.16227766,
3.720215048,
3.358571125,
5.513619501,
7.976966842,
6.170899448,
8.782084035,
0
]
num1520 = [
77,
86.2,
108,
122.4,
139.4,
174,
193.4,
204.5,
195.5
]
error1520 = [
7.678541528,
7.268562444,
4.833218389,
8.829949037,
9.391911414,
15.09701957,
12.56853213,
12.83793208,
18.7383297
]
num1015 = [
76.8,
101.8,
125.4,
143,
166.8,
220.8,
235.8,
253.5
]
error1015 = [
3.025227264,
2.61380948,
4.635515074,
2.529822128,
2.876108482,
9.824052117,
9.04831476,
22.27386361
]
num0510 = [
111.8,
154.4,
190.4,
206,
249.4,
302.8,
317.6666667
]
error0510 = [
5.581397674,
13.03717761,
12.53347518,
10.92886087,
9.743100123,
12.40773952,
15.30674022
]
num0005 = [
132,
178.4,
227,
262.6,
304.2,
334.5
]
error0005 = [
7.391887445,
9.119649116,
9.53310023,
8.605114758,
7.946823265,
11.40350823
]
num0000 = [
204.4,
272.8,
338,
356.4,
399
]
error0000 = [
7.319016327,
9.925321153,
9.363759929,
9.841138146,
11.35605565
]
plt.suptitle('Cells In Wound With Time')
plt.errorbar(iteration, num2025, yerr=error2025, ecolor="black", fmt='bx--', capsize=5, label="23.5%")
plt.errorbar(iteration, num1520, yerr=error1520, ecolor="black", fmt='gx--', capsize=5, label="17.3%")
plt.errorbar(iteration1, num1015, yerr=error1015, ecolor="black", fmt='rx--', capsize=5, label="12.9%")
plt.errorbar(iteration2, num0510, yerr=error0510, ecolor="black", fmt='cx--', capsize=5, label="7.0%")
plt.errorbar(iteration3, num0005, yerr=error0005, ecolor="black", fmt='mx--', capsize=5, label="2.7%")
plt.errorbar(iteration4, num0000, yerr=error0000, ecolor="black", fmt='x--', capsize=5, label="0.0%")

plt.xlabel('Iteration after wound')
plt.ylabel('Num cells in wound')
plt.legend(title="Confluence Senescence", loc="upper center", ncol=3)

plt.ylim(50,550)
# plt.xlim(1,15)

plt.show()