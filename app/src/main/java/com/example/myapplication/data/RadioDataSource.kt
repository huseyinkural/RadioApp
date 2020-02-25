package com.example.myapplication.data

import com.example.myapplication.data.remote.Radio
import com.example.myapplication.data.remote.RadioServiceProvider
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class RadioDataSource {

    private val radioServiceProvider = RadioServiceProvider()

    fun fetchPopularRadios(): Observable<Resource<List<Radio>>>{
        return Observable.create{ emitter ->
            emitter.onNext(Resource.loading())

            radioServiceProvider
                .getRadioService()
                .getPopularRadios()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { radioList ->
                        emitter.onNext(Resource.success(radioList))
                        emitter.onComplete()
                    },

                    {
                        error -> emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    })

        }
    }

    fun fetchLocalRadios(): Observable<Resource<List<Radio>>>{
        return Observable.create{
            emitter ->  emitter.onNext(Resource.loading())

            radioServiceProvider
                .getRadioService()
                .getLocationRadios()
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { radioList ->
                        emitter.onNext(Resource.success(radioList))
                        emitter.onComplete()
                    },
                    { error -> emitter.onNext(Resource.error(error.message ?: ""))
                        emitter.onComplete()
                    }
                )

        }
    }
}