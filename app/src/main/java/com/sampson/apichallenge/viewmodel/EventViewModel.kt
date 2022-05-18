package com.sampson.apichallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sampson.apichallenge.controller.EventsRepository
import com.sampson.apichallenge.model.Events
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class EventViewModel(private val eventsRepository: EventsRepository): ViewModel() {

    private val allEventsLiveData = MutableLiveData<Events>()
    private val errorLiveData = MutableLiveData<String>()

    val allEvents: LiveData<Events>
    get() = allEventsLiveData

    val error: LiveData<String>
    get() = errorLiveData

    private var disposable = CompositeDisposable()

    fun getAllEvents(){
        disposable.add(eventsRepository.getAllEvents()
            .subscribeOn(Schedulers.io())
            .map { it }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                allEventsLiveData.postValue(it)
            }, {
                error ->
                Log.d("EventViewModel","error: ${error.localizedMessage}")
            }))
    }
}