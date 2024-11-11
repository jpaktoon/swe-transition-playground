package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubdomainVisits {

    public static List<String> subdomainVisits(String[] cpdomains) {
        HashMap<String, Integer> counting = new HashMap<>();
        for (String cpdomain : cpdomains) {
            //List<String> domains = hashDomain(cpdomain);
            String[] countDomain = cpdomain.split("\\s+");
            int count = Integer.parseInt(countDomain[0]);
            List<String> subDomains = hashDomains(countDomain[1]);
            for (String subDomain : subDomains) {
                counting.put(subDomain, counting.getOrDefault(subDomain, 0) + count);
            }
        }

        return getResult(counting);
    }

    public static List<String> hashDomains(String domains) {
        String[] subParts = domains.split("\\.");
        List<String> subDomains = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = subParts.length - 1; i >= 0 ; i --)
        {
            subDomains.add(subParts[i] + temp);
            temp.insert(0, "." + subParts[i]);
        }
        return subDomains;
    }

    public static List<String> getResult(Map<String, Integer> results) {
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : results.entrySet()) {
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }

    public static void main(String[] args) {
        String[] cpdomains = new String[] {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(subdomainVisits(cpdomains));
    }
}
