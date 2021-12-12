

import java.util.ArrayList;

public class KMP {

	public static ArrayList<Integer> runKMP(final String text, final String pattern) { // complete this function
		int t=0, p=0, textLen = text.length(), patLen = pattern.length();
		int[] F = computeFailure(pattern);
		ArrayList<Integer> occ = new ArrayList<>();
		while (t < textLen){
			if (pattern.charAt(p)==text.charAt(t)){
				if (p==patLen-1){
					occ.add(t-p);
					assert F != null;
					p=F[p];
				}
				else {
					p++;
				}
				t++;
			}
			else {
				if (p!=0){
					assert F != null;
					p=F[p-1];
				}
				else {
					t++;
				}
			}
		}
		return occ;
	}

	private static int[] computeFailure(final String pattern) { // complete this function
		int patLen = pattern.length(), pref = 0, suff = 1;
		int[] F = new int[patLen];
		F[0] = 0;
		while (suff != patLen){
			if (pattern.charAt(suff)==pattern.charAt(pref)){
				pref++;
				F[suff] = pref;
				suff++;
			}
			else if (pref==0){
				F[suff] = 0;
				suff++;
			}
			else pref = F[pref-1];
		}
		return F;
	}
}
