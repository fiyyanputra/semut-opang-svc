import config.Config;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by fiyyanp on 10/12/2016.
 */
public class OpangService extends Application<Config> {
    public static void main(String[] args) throws Exception {
        new OpangService().run(args);
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public void initialize(Bootstrap<Config> bootstrap) {
        bootstrap.addBundle(new OpangBundle());
    }

    @Override
    public void run(Config configuration, Environment environment) throws Exception {

    }
}

