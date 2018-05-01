import matplotlib.pyplot as plt

# bins=["00-05","05-10","10-15","15-20","20-25"]

time_to_heal = [
34.8,
39.6,
44.4,
49.4,
48
]

bins = [
2.74,
6.98,
12.88,
17.28,
23.54
]

yerror = [
1.073312629,
1.314534138,
1.314534138,
1.867618805,
1.697056275
]

xerror = [
0.060663004,
0.357099426,
0.450244378,
0.666573327,
0.885708756
]

plt.suptitle('Time Taken For Wound To Heal At Varying Senescence')
plt.errorbar(bins, time_to_heal, yerr=yerror, xerr=xerror, ecolor="black", fmt='bx--', capsize=5)
plt.xlabel('Confluence Senescnece (%)')
plt.ylabel('Time to Heal (Hrs)')
plt.show()