package springnz.orientdb.plugin.functions;

import com.orientechnologies.common.log.OLogManager;
import com.orientechnologies.orient.core.command.OCommandContext;
import com.orientechnologies.orient.core.db.record.OIdentifiable;
import com.orientechnologies.orient.core.sql.functions.OSQLFunctionAbstract;

import java.util.ArrayList;
import java.util.List;

public class PowFunction extends OSQLFunctionAbstract {

    public PowFunction() {
        super("pow", 2, 3);
    }

    public Object execute(Object iThis, OIdentifiable iCurrentRecord, Object iCurrentResult,
                          final Object[] iParams, OCommandContext iContext) {
        if (iParams[0] == null || iParams[1] == null) {
            return null;
        }
        if (!(iParams[0] instanceof Number) || !(iParams[1] instanceof Number)) {
            return null;
        }

        final long base = ((Number) iParams[0]).longValue();
        final long power = ((Number) iParams[1]).longValue();

        if (iParams.length == 3) { // modular exponentiation
            if (iParams[2] == null) {
                return null;
            }
            if (!(iParams[2] instanceof Number)) {
                return null;
            }

            final long mod = ((Number) iParams[2]).longValue();
            if (power < 0) {
                OLogManager.instance().warn(this, "negative numbers as exponent are not supported");
            }
            return modPow(base, power, mod);
        }

        return power > 0 ? pow(base, power) : 1D / pow(base, -power);
    }

    public String getSyntax() {
        return "pow(<base>, <power> [, <mod>])";
    }

    public boolean aggregateResults() {
        return false;
    }

    private double pow(long base, long power) {
        double r = 1;
        List<Boolean> bits = bits(power);
        for (int i = bits.size() - 1; i >= 0; i--) {
            r *= r;
            if (bits.get(i)) {
                r *= base;
            }
        }

        return r;
    }

    private double modPow(long base, long power, long mod) {
        double r = 1;
        List<Boolean> bits = bits(power);
        for (int i = bits.size() - 1; i >= 0; i--) {
            r = (r * r) % mod;
            if (bits.get(i)) {
                r = (r * base) % mod;
            }
        }

        return r;
    }

    private List<Boolean> bits(long n) {
        List<Boolean> bits = new ArrayList<Boolean>();
        while (n > 0) {
            bits.add(n % 2 == 1);
            n /= 2;
        }

        return bits;
    }
}
