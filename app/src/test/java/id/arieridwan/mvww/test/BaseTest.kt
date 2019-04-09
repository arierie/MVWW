package id.arieridwan.mvww.test

import id.arieridwan.mvww.di.component.DaggerTestApplicationComponent
import id.arieridwan.mvww.di.component.TestApplicationComponent
import org.junit.Before

/**
 * Created by arieridwan on 09/04/19.
 */

abstract class BaseTest {

    lateinit var testApplicationComponent: TestApplicationComponent

    @Before
    open fun setUp(){
        this.initInjection()
    }

    open fun initInjection() {
        testApplicationComponent = DaggerTestApplicationComponent.builder()
                .application(this)
                .build()
    }

}