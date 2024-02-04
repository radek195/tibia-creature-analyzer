package integration

import common.DbHelper
import spock.lang.Specification

class IntegrationSpec extends Specification {
    DbHelper dbHelper

    def setup() {
        dbHelper = new DbHelper()
    }
}
