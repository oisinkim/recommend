package ie.smarter.recommend.guice

import com.wordnik.swagger.config.ConfigFactory
import com.wordnik.swagger.config.ScannerFactory
import com.wordnik.swagger.config.SwaggerConfig
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader
import com.wordnik.swagger.reader.ClassReaders
import com.yammer.dropwizard.assets.AssetsBundle
import com.yammer.dropwizard.config.Environment

class SwaggerBundle extends AssetsBundle {

    public static final String DEFAULT_PATH = '/swagger'

    public SwaggerBundle() {
        super(DEFAULT_PATH)
    }

    @Override
    public void run(Environment environment) {
        super.run(environment)

        environment.addResource(new ApiListingResourceJSON())
        environment.addProvider(new ApiDeclarationProvider())
        environment.addProvider(new ResourceListingProvider())

        // Swagger Scanner, which finds all the resources for @Api Annotations
        ScannerFactory.setScanner(new DefaultJaxrsScanner())

        // Add the reader, which scans the resources and extracts the resource information
        ClassReaders.setReader(new DefaultJaxrsApiReader())

        SwaggerConfig config = ConfigFactory.config()
        config.setApiVersion('1.0')
    }
}
