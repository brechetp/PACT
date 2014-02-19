package adaBoost;

import java.util.ArrayList;


public class mainada2 {

	public static void main(String[] args) 
	{
		double[][] X=
			{
				
				{-0.733779,	 0.583635,	 2.266879,	 -0.603262,	 -0.206634,	 3.896376,	 1.562730,	 4.355851,	 -4.592190,	 -0.323958,	 -0.452850,	 1.395081,	 1.519777,	 1.127040,	 2.076338,	 1.229900,	 0.431894,	 -0.158252,	 -0.327717,	 -0.405391},
				{-0.990026,	 -0.805453,	 -0.003826,	 0.290809,	 0.262934,	 -0.254662,	 -0.379359,	 -0.270615,	 -0.175976,	 -1.202145,	 -0.052269,	 0.620231,	 0.347131,	 -0.050203,	 0.175899,	 1.383524,	 0.115682,	 -1.623128,	 -2.206879,	 -2.256889},
				{-1.189933,	 -0.854214,	 0.195245,	 0.198954,	 0.230809,	 -0.002215,	 -0.587177,	 0.311224,	 -0.788565,	 -1.239817,	 0.028755,	 0.834525,	 0.870570,	 0.486317,	 0.395215,	 1.958007,	 0.476244,	 -1.006974,	 -1.379663,	 -1.435926},
				{-0.221454,	 0.413043,	 0.417214,	 -0.835415,	 -0.701179,	 -0.195805,	 -0.847998,	 -0.326785,	 0.324771,	 0.608299,	 -0.887212,	 -0.779525,	 0.379474,	 -0.077895,	 -1.710134,	 -0.933420,	 0.320721,	 0.397517,	 0.509338,	 2.247199},
				{-0.459883,	 -0.382618,	 -0.187224,	 0.325255,	 0.215760,	 -0.390331,	 -1.009308,	 -1.064716,	 0.766402,	 -0.638833,	 -0.738082,	 -0.191184,	 -0.554525,	 -1.246158,	 -1.922080,	 -0.597785,	 -1.398324,	 0.260038,	 1.677513,	 2.819742},
				{-0.484150, 	-0.488011, 	0.152832, 	0.392766, 	0.316650, 	0.240716, 	0.831855, 	-0.069234, 	-0.167954, 	-0.903809, 	-0.507603, 	0.625347, 	0.503304, 	-0.426903, 	-1.142642, 	0.414726, 	-0.578695, 	-2.237914, 	-1.680727, 	0.513906},
				{ 0.038017, 	0.096045, 	0.074111, 	-0.413458, 	-0.426290, 	0.065476, 	-0.154905, 	-0.787713, 	0.751136, 	0.071209, 	-0.320814, 	-0.373383, 	-0.144877, 	0.648378, 	0.332232, 	-0.222507, 	0.074588, 	-0.822975, 	-1.490888, 	-0.371697},
				{-1.310250, 	-1.457794, 	-0.782189, 	1.304809, 	1.226393, 	-0.956994, 	-1.706266, 	1.947922, 	-2.425098, 	-1.584661, 	0.798773, 	1.534656, 	1.462098, 	0.981727, 	0.751653, 	1.375705, 	0.176920, 	0.479957, 	-0.414445, 	-0.407814},
				{-0.712736, 	-0.313003, 	0.590198, 	-0.160223, 	-0.110922, 	0.605036, 	0.836565, 	0.753334, 	-1.056450, 	-0.840905, 	-0.478640, 	0.508486, 	0.689509, 	0.131822, 	-0.175312, 	-0.073033, 	-1.212786, 	-1.292321, 	-0.086674, 	-0.294583},
				{-0.959141, 	-1.000764, 	-0.562823, 	0.667563, 	0.630067, 	-0.669728, 	-1.371392, 	0.133905, 	-0.577019, 	-0.918440, 	0.811740, 	0.878039, 	0.120668, 	0.345848, 	1.143425, 	1.334865, 	-0.488462, 	-0.917921, 	-0.807703, 	-0.373336},
				{-0.747525,	 -0.926094,	 -0.535237,	 0.782414,	 0.929729,	 -0.453282,	 0.006514,	 0.002857,	 -0.419292,	 -0.689439,	 1.258715,	 1.188767,	 0.297601,	 1.544854,	 0.799029,	 0.542121,	 0.221256,	 0.123586,	 -0.500609,	 -0.314005},
				{-0.700518,	 -1.175047,	 -1.411502,	 1.122556,	 1.353749,	 -1.349155,	 -0.736634,	 -0.710694,	 0.277063,	 -0.728525,	 1.830166,	 0.905032,	 -0.129111,	 1.880707,	 0.703605,	 0.290499,	 0.296589,	 0.573554,	 -0.537199,	 -0.414888},
				{-0.678966,	 -1.157266,	 -1.273213,	 1.169165,	 1.206133,	 -1.237123,	 -1.687020,	-0.730490,	 0.293979,	 -0.873983,	 1.518852,	 1.004631,	 0.319235,	 0.842946,	 0.147106,	 -0.092256,	 -0.053725,	 0.238341,	 -0.180466,	 -0.374340},
				{-0.515545,	 -1.012542,	 -1.132900,	 0.779758,	 0.832161,	 -1.101788,	 -2.508447,	 -0.832337,	 0.446155,	 -0.753969,	 1.394266,	 0.805292,	 0.739465,	 0.389930,	 0.300767,	 0.442739,	 0.353788,	 0.927041,	 0.125385,	 -0.129977},
				{-0.668444,	 -1.136180,	 -1.257027,	 1.055236,	 1.104039,	 -1.279025,	 -3.030614,	 -0.816392,	 0.384561,	 -0.857656,	 1.447106,	 0.884350,	 0.465165,	 0.644309,	 0.105657,	 0.160679,	 -0.574796,	 -0.400274,	 -0.326891,	 -0.312649},
				{-0.917904, 	-0.677504, 	1.152258, 	0.488704, 	0.537794, 	0.571506, 	1.123702, 	2.594247, 	-2.972255, 	-1.614275, 	0.184102, 	2.375238, 	1.063179, 	0.722262, 	0.965813, 	0.544161, 	0.968377, 	1.066506, 	0.872707, 	0.331517}, 	
				{-0.920449, 	-1.435833, 	-1.474554, 	2.318597, 	2.636607, 	-1.392814, 	-0.155344, 	-0.281832, 	-0.199648, 	-1.256215, 	1.654382, 	1.613714, 	0.198664, 	0.971447, 	0.205484, 	0.276914, 	-0.853840, 	-0.023138, 	-0.713646, 	-0.358919}, 	
				{-0.908910, 	-1.336174, 	-1.213869, 	1.480884, 	1.632322, 	-1.248220, 	0.026271, 	-0.150533, 	-0.326049, 	-0.852920, 	1.624218, 	1.467520, 	-0.055312, 	2.209889, 	3.098047, 	0.726094, 	-0.193285, 	0.481415, 	-0.471000, 	-0.644058}, 	
				{-0.524369, 	-0.612501, 	-0.557961, 	0.440984, 	0.661399, 	-0.074647, 	-1.515740, 	0.305462, 	-0.644608, 	-0.187335, 	1.324839, 	0.739207, 	0.592862, 	1.656397, 	-0.173114, 	0.483547, 	0.195122, 	0.078162, 	-0.458776, 	-0.232230}, 	
				{-0.738700, 	-1.307525, 	-1.557303, 	1.971331, 	2.208190, 	-1.479752, 	0.288474, 	-0.843721, 	0.391638, 	-0.939007, 	1.853420, 	1.374935, 	-0.541918, 	1.240517, 	0.840795, 	-0.033363, 	-0.119124, 	0.467164, 	-0.326559, 	-0.266715}, 	
				{-0.588516, 	-0.434854, 	-1.029074, 	0.590241, 	0.338067, 	-1.055199, 	1.324350, 	-1.341850, 	1.221304, 	-0.434783, 	0.443472, 	-0.606601, 	-2.102812, 	-0.810767, 	-0.696508, 	-0.530748, 	-1.205274, 	-0.807832, 	-0.989890, 	-1.016379},
				{0.015278, 		0.236741, 	-0.473811, 	-0.044347, 	-0.351886, 	0.030917, 	1.101308, 	-0.377764, 	0.569199, 	0.334418, 	0.028305, 	-1.042660, 	-1.721028, 	-0.523721, 	-0.389514, 	-0.584996, 	-0.990042, 	-0.592480, 	-1.163066, 	-1.126086},
				{0.173268, 		0.263477, 	-0.297865, 	0.076251, 	-0.252587, 	0.272349, 	1.334263, 	-0.317087, 	0.520757, 	0.331166, 	-0.091521, 	-0.843078, 	-1.753564, 	-0.436157, 	-0.475527, 	-0.293745, 	-0.587165, 	0.028827, 	-0.592960, 	-0.389670},
				{0.463965, 		1.212250, 	0.513641, 	-0.849250, 	-1.036304, 	1.115356, 	1.499460, 	0.513896, 	0.193439, 	1.335375, 	-0.869763, 	-1.239227, 	-0.774343, 	-0.801405, 	-0.020914, 	-0.429912, 	0.008614, 	0.123385, 	-0.212716, 	-0.344705},
				{0.217390, 		0.741543, 	0.009732, 	-0.605471, 	-0.851625, 	0.162881, 	0.648932, 	0.082064, 	0.428665, 	0.812562, 	-0.579679, 	-1.360746, 	-0.775998, 	-0.810673, 	-0.607435, 	-0.714410, 	-0.839981, 	-0.462737, 	-0.553554, 	-0.627251},
				{0.089267,	 -0.095258,	 -0.578261,	 0.285935,	 -0.057457,	 -0.670273,	 0.134617,	 -1.091812,	 1.122718,	 -0.258509,	 -0.192499,	 -0.719783,	 -1.428489,	 0.109311,	 -0.154524,	 0.432230,	 -0.233111,	 0.407495,	 0.302180,	 0.888105},
				{0.790128,	 0.471417,	 -0.146169,	 -0.451512,	 -0.774253,	 -0.200681,	 0.241486,	 -1.048274,	 1.300596,	 0.158851,	 -0.842048,	 -1.205428,	 -0.976351,	 -0.871952,	 0.189103,	 0.713169,	 0.821350,	 1.006403,	 0.891550,	 1.520005},
				{1.116292,	 0.774199,	 -0.298711,	 -0.722898,	 -1.267809,	 -0.585119,	 -0.372726,	 -0.628926,	 1.170877,	 0.629927,	 -0.953433,	 -1.934859,	 -0.971700,	 -0.857918,	 0.176461,	 0.291201,	 0.331971,	 0.599672,	 0.202430,	 0.744364},
				{0.612452,	 0.819825,	 0.692458,	 -0.402454,	 -0.486457,	 2.460571,	 1.338128,	 0.853329,	 -0.575529,	 0.977668,	 0.123290,	 -0.068897,	 -1.048472,	 -0.135734,	 0.477197,	 0.053494,	 0.020012,	 0.071806,	 -0.515266,	 -0.748686},
				{0.563918,	 0.827254,	 0.115789,	 -0.686192,	 -1.079886,	 0.360915,	 1.433774,	 -0.139533,	 0.654541,	 0.628231,	 -0.710886,	 -1.430402,	 -0.887269,	 -0.599424,	 -0.033318,	 -0.223277,	 -0.031871,	 0.208450,	 -0.226433,	 -0.487460},
				{0.665738, 		0.826122, 	0.950496, 	-1.247913, 	-0.934722, 	0.515242, 	0.015676, 	0.414272, 	-0.105667, 	1.095136, 	-0.640531, 	-0.391859, 	1.342011, 	-0.379401, 	0.152631, 	-1.018534, 	-0.302901, 	-0.173167, 	0.888561, 	0.708854},
				{-0.017814, 	0.236446, 	0.346958, 	-0.889328, 	-0.642097, 	0.025395, 	0.286718, 	-0.034533, 	-0.037337, 	0.553169, 	-0.299966, 	-0.571534, 	0.359086, 	-0.256647, 	-2.219316, 	-1.938486, 	-0.613756, 	-1.496243, 	-0.362179, 	0.502018},
				{2.014515, 		1.764851, 	1.258443, 	-1.403957, 	-1.407998, 	0.691415, 	0.510213, 	1.110873, 	0.153445, 	1.730753, 	-1.550520, 	-1.191246, 	-0.081556, 	-0.626626, 	-0.001368, 	0.263319, 	3.229004, 	2.834424, 	2.034113, 	0.256369},
				{1.597053, 		1.467137, 	1.201426, 	-1.494010, 	-1.286799, 	1.073292, 	-0.038526, 	1.166720, 	-0.152889, 	1.763902, 	-0.964991, 	-0.762361, 	0.863571, 	-0.480744, 	-0.201108, 	-2.030854, 	0.054030, 	-0.272874, 	0.643322, 	0.287158},
				{0.769764, 		0.895406, 	0.901149, 	-1.253623, 	-0.991301, 	0.985107, 	0.484038, 	0.253602, 	0.114884, 	1.010110, 	-0.568229, 	-0.538519, 	0.707644, 	-0.165940, 	-0.393775, 	-2.087191, 	-0.358745, 	-1.074617, 	0.280678, 	0.079961},
				{1.019902,	 1.443987,	 1.473550,	 -1.606660,	 -1.222831,	 1.161649,	 -0.709153,	 1.235002,	 -0.783066,	 1.897839,	 -1.231676,	 -0.849698,	 0.763907,	 -2.146863,	 -0.531940,	 -1.572790,	 -0.075230,	 0.238279,	 1.602885,	 1.014823},
				{0.561033,	 0.908453,	 1.063458,	 -1.280530,	 -0.936484,	 0.628517,	 -0.544140,	 0.668568,	 -0.469737,	 1.277135,	 -0.754492,	 -0.470519,	 1.045517,	 -1.596326,	 -0.550155,	 -1.206183,	 -0.338113,	 -0.267998,	 1.027855,	 0.403934},
				{1.994321,	 2.148965,	 1.429140,	 -1.604519,	 -1.677088,	 0.944161,	 -0.466415,	 1.196552,	 0.273978,	 1.808147,	 -1.878327,	 -0.999171,	 1.456474,	 0.159862,	 -0.207992,	 -1.197439,	 1.932357,	 1.249464,	 2.045441,	 0.889619},
				{1.193907,	 2.819734,	 3.429199,	 -0.952259,	 -0.898797,	 0.949669,	 1.153581,	 0.286485,	 0.415623,	 2.310377,	 -1.462632,	 -0.619475,	 0.661276,	 0.952590,	 0.416698,	 2.079119,	 2.887582,	 2.968235,	 0.310646,	 -0.172956},
				{0.215524,	 -0.144019,	 -0.597535,	 -0.330419,	 -0.360836,	 -0.365310,	 0.018148,	 -0.827206,	 0.795102,	 -0.024208,	 0.573626,	 -0.446019,	 -0.107613,	 0.250741,	 -0.681943,	 -0.238475,	 -0.329814,	 -0.421232,	 0.060680,	 -0.591069}
			};
		
		int[] Y ={1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,4};
		int T=20;
		ClassiFinal[] classi = new ClassiFinal[4];
		
		//creation des classi
		for (int i=1;i<5;i++)
		{
			int[] Y2 = new int[40];
			for(int j=0;j<40;j++)
			{
				if (i==Y[j])
					Y2[j]=1;
				else Y2[j]=-1;
			}
			classi[i-1] =AdaBoost.adaBoost(X, Y2, T);
		}
		
		double[][] test =
			{
				{-1.272916 ,	-1.417264, 	-0.574585, 	1.401585, 	1.270023, 	-0.738277, 	0.194547, 	1.474232, 	-1.962150, 	-1.603461, 	0.561820, 	1.686156, 	1.170453, 	0.343258, 	-0.201429, 	0.813465, 	-0.516391, 	-0.720986, 	-1.397320, 	-1.290588},
				{-0.258109, 	-0.351108, 	-0.342917, 	0.489328, 	0.196454, 	-0.681423, 	-0.374083, 	-1.392900, 	1.125817, 	-0.795811, 	-0.908412, 	-0.411808, 	-1.362156, 	-1.511173, 	-1.038142, 	0.734258, 	0.547297, 	1.928656, 	2.569895, 	2.235217},
				{-0.220945, 	-0.281030, 	-0.218691, 	0.448712,	0.177099, 	-0.429837, 	-0.258429, 	-1.324139, 	1.091276, 	-0.629433, 	-0.703979, 	-0.352051, 	-1.304214, 	-0.809174, 	-1.671480, 	0.110790, 	-0.122439, 	1.385569, 	2.011157, 	1.943016},
				{-0.158326, 	-0.130174, 	-0.146189, 	0.319455, 	0.145673, 	0.058079, 	0.337680, 	-0.877378, 	0.750157, 	-0.423474, 	-0.749594, 	-0.316894, 	-0.805548, 	-0.924153, 	-1.357121, 	0.140530, 	-0.210694, 	0.397280, 	1.614081, 	2.747553},
				{-0.699160, 	-0.412594, 	0.027335, 	0.340897, 	0.292980, 	0.054273, 	-0.543726, 	-0.352553, 	0.017422, 	-0.767964, 	-0.332511, 	0.231200, 	-0.258051, 	-0.771129, 	-1.001692, 	0.382036, 	-0.611893, 	-1.754224, 	-0.833441, 	0.822315},
				{-0.903649, 	-1.313923, 	-1.247746, 	1.465299, 	1.611426, 	-1.266786, 	0.304021, 	-0.018462, 	-0.442544, 	-0.857585, 	1.638375, 	1.452451, 	-0.148553, 	1.844511, 	2.927634, 	0.936421, 	0.164059, 	0.387706, 	-0.440262, 	-0.631448},
				{-0.763307, 	-1.223780, 	-1.102099, 	1.381122, 	1.323485, 	-1.156637, 	-1.366052, 	-0.668638, 	0.209986, 	-1.099095, 	1.315252, 	1.373387, 	0.176939, 	0.316593, 	-0.070460, 	-0.274959, 	-0.592207, 	-0.514760, 	-0.556530, 	-0.743369},	
				{-0.794871, 	-1.185328, 	-1.293813, 	1.114022, 	1.248878, 	-1.208099, 	-0.368484, 	-0.585281, 	0.124653, 	-0.877516, 	1.593584, 	0.980337, 	0.428802, 	1.009955, 	1.036754, 	0.697781, 	-0.453936, 	-0.303638, 	-0.605695, 	-0.526331},
				{-0.780956, 	-1.122751, 	-1.190325, 	0.897843, 	1.061097, 	-1.108226, 	-0.445514, 	-0.463940, 	0.006868, 	-0.870943, 	1.459913, 	0.828810, 	0.846796, 	0.808866, 	0.346997, 	0.450015, 	-0.823723, 	-0.252530, 	-0.668610, 	-0.506456}, 
				{-0.729536, 	-1.209924, 	-1.180378, 	1.606314, 	1.629779, 	-1.231255, 	0.097268, 	-0.879671, 	0.441355, 	-1.032728, 	1.404465, 	1.358303, 	-0.225078, 	1.055397, 	0.030945, 	-0.098703, 	-0.370159, 	0.344055, 	0.016297, 	-0.082421}, 	  																						
				{0.310216, 		0.720358, 	-0.066622, 	-0.443224, 	-0.816211, 	0.500727, 	1.196233, 	-0.190387, 	0.634327, 	0.774253, 	-0.762458, 	-1.454347, 	-1.261040, 	-0.445235, 	-0.561502, 	-0.268584, 	-0.221367, 	0.498384, 	0.332074, 	0.706436},
				{0.466510, 		1.073289, 	0.555166, 	-0.914292, 	-1.113205, 	0.958741, 	1.279637, 	0.163113, 	0.344989, 	0.892712, 	-0.956990, 	-1.317332, 	-0.609734, 	-1.014854, 	0.076293, 	-0.172043, 	0.360913, 	0.535654, 	0.298250, 	0.376365},
				{-0.190738, 	-0.374132, 	-1.069160, 	0.585144, 	0.259943, 	-1.037212, 	0.010010, 	-1.412178, 	1.340312, 	-0.375766, 	0.584035, 	-0.675014, 	-2.336662, 	-0.677505, 	-0.239776, 	0.009252, 	-0.825466, 	-0.665631, 	-1.052593, 	-1.184206},
				{-0.005256, 	0.438785, 	0.019454, 	-0.179989, 	-0.389843, 	0.761746, 	0.930992, 	0.152648, 	0.010448, 	0.648657, 	-0.087657, 	-0.751040, 	-1.260970, 	-0.535378, 	-0.382548, 	-0.276358, 	-0.488488, 	-0.517687, 	-1.067270, 	-0.685895},
				{-0.038687, 	-0.115491, 	-0.410540, 	0.528225, 	0.322848, 	0.237007, 	1.584712, 	-0.486038, 	0.445664, 	-0.002510, 	0.506093, 	-0.073300, 	-1.818856, 	-0.757990, 	-0.140210, 	0.129382, 	-0.746037, 	-0.751669, 	-0.962437, 	-0.835147},
				{0.513517, 		0.900795, 	0.984742, 	-1.309239, 	-1.009843, 	0.791317, 	-0.036492, 	0.344676, 	-0.043062, 	0.892288, 	-0.629324, 	-0.372335, 	1.608125, 	1.174294, 	-0.463241, 	-0.971448, 	0.828759, 	-0.434381, 	0.548963, 	0.065422},
				{0.662175, 		1.075242, 	1.352479, 	-1.387343, 	-1.036305, 	1.316167, 	0.281489, 	0.775306, 	-0.426811, 	1.170975, 	-0.705491, 	-0.242557, 	1.557179, 	1.040625, 	-0.309775, 	-0.711225, 	0.941519, 	-0.696990, 	0.549371, 	-0.168222},
				{0.120661, 		0.181644, 	0.413709, 	-0.950513, 	-0.675381, 	0.238580, 	-0.024056, 	-0.158745, 	0.080418, 	0.391385, 	-0.029295, 	-0.149830, 	1.255513, 	-0.551836, 	0.667459, 	-0.638115, 	-0.752809, 	-0.837873, 	0.455415, 	-1.045377},
				{0.825596, 		1.307245, 	1.291149, 	-1.517078, 	-1.190567, 	0.605801, 	-0.934128, 	0.911401, 	-0.532268, 	1.630460, 	-1.310411, 	-0.989204, 	0.373672, 	-2.399156, 	-0.779007, 	-1.546398, 	-0.215067, 	0.330925, 	1.855883, 	1.262807},
				{-0.106058, 	0.366767, 	0.727685, 	-1.134722, 	-0.779232, 	0.513244, 	-1.305510, 	0.110816, 	-0.245178, 	0.554583, 	-0.456536, 	-0.387273, 	0.995461, 	-1.713273, 	-0.699987, 	-1.743443, 	-1.620008, 	-1.421365, 	0.166650, 	-1.320666}
			};
		int[] Ytest = {1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4};
		int[] Ysortie = new int[20];
		for(int j=0;j<20;j++)
		{
			int indiceMax=0;
			double resultMax =-1;
			for(int k=1;k<5;k++)
			{
				double result = classi[k-1].result(test[j])/(classi[k-1].valeurA());
				if (result>resultMax)
				{
					indiceMax=k;
					resultMax=result;
				}
			}
			if (indiceMax==0)
				System.out.println("bordel !!!");
			Ysortie[j]=indiceMax;
			
		}
		
		for(int j=0;j<20;j++)
		{
			System.out.print("Ytest ="+Ytest[j]+"   ");
			System.out.println("Ysortie ="+Ysortie[j]);
		}
	}
}
