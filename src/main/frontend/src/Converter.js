import React, { Component } from 'react';
import {useState, useEffect} from 'react';

export default class Converter extends Component {

    constructor(props) {
        super(props);
        this.state = {inputNumber: '', fromSelect: 'DECIMAL', toSelect: 'ROMAN', outputResult: ''};
    }

    handleInputNumChange(event) {
        this.setState({
            inputNumber: event.target.value
        })
    }
    handleFromChange(event)  {
        this.setState({
            fromSelect: event.target.value
        })
     }

    handleToChange(event)  {
        this.setState({
            toSelect: event.target.value
        })
    }

    async callAPI() {
      const response = await fetch('http://localhost:8080/convert/number?inputNumber=' + this.state.inputNumber
                                               + "&inputFormat=" + this.state.fromSelect
                                               + "&outputFormat=" + this.state.toSelect,
         {method: 'GET',
         headers: {
                   'Access-Control-Allow-Origin': '*',
                 },
         });

      const output = await response.text();
      if (response.ok) {
         this.setState({
                      outputResult: output
                  });
      } else {
        alert(output);
      }
    }

  render() {
    return(
       <div  style={{margin: '50px'}}>
              <div class="form-group col-2" style={{margin: '20px'}}>
                  <label for="fromSelect">From</label>
                  <select id="fromSelect"  onChange={this.handleFromChange.bind(this)} class="form-control  form-control-lg">
                      <option value="BINARY">Binary</option>
                      <option value="DECIMAL" selected>Decimal</option>
                  </select>
              </div>
              <div class="form-group col-2" style={{margin: '20px'}}>
                  <label for="toSelect">To</label>
                  <select id="toSelect" onChange={this.handleToChange.bind(this)} class="form-control  form-control-lg">
                      <option value="ROMAN" selected>Roman</option>
                  </select>
              </div>
              <div class="form-group col-2" style={{margin: '20px'}}>
                  <label for="inputNumber">Input:</label>
                  <input type="text" id="inputNumber" onChange={this.handleInputNumChange.bind(this)}  class="form-control  form-control-lg"/>
              </div>
              <div class="form-group col-2" style={{margin: '20px'}}>
                  <label for="outputResult">Output:</label>
                  <textarea id="outputResult" readonly class="form-control form-control-lg" value={this.state.outputResult} />
              </div>
              <div class="form-group" style={{margin: '20px'}}>
                  <button size="sm"  class="form-group col-2" onClick={this.callAPI.bind(this)}>Convert</button>
              </div>
            </div>
    )
  }

}

