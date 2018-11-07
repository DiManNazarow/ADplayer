package dmitriy_nazarov.ru.adplayer.features

import io.reactivex.Observable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

//fun Observable<*>.retryWithDelay(maxAttempts: Int, attempts: Observable<*>): Observable<Int> {
//    return attempts.flatMap {  }
////    return retryWhen { (errors: Observable<Exception>) -> Observable<Int> in
////            return errors.flatMapWithIndex { (e, a) -> Observable<Int> in
////                    if a >= maxAttempts - 1 {
////                        return Observable.error(e)
////                    }
////
////                return Observable.timer((a + 1) * 50), Schedulers.newThread())
////            }
////    }
//}

class RetryWithDelay (val delay: Long, val maxRetries: Int) : Function<Observable<Throwable>, Observable<*>> {

    var retryCount: Int = 0

    override fun apply(attempts: Observable<Throwable>): Observable<*> {
        return attempts.flatMap(Function<Throwable, Observable<*>> { throwable ->
            if (++retryCount < maxRetries) {
                return@Function Observable.timer(delay, TimeUnit.MILLISECONDS)
            }
            Observable.just(Observable.empty<Any>())
        })
    }

}