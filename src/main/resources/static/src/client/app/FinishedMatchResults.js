import React from 'react';
import {Tabs, Tab} from 'material-ui/Tabs';

import MatchResults from './MatchResults';

class FinishedMatchResults extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    numberOfFinishedRounds: 0
	    };
	}

	componentDidMount() {
        fetch(window.backendHost + '/api/matches/search/findMaxFinishedRound')
        .then((response) => { return response.text() })
        .then( (text) => {this.setState({numberOfFinishedRounds: Number(text) }); });
	}

    render() {
        var tabNodes = getTabs(this);

        return (
            <Tabs inkBarStyle={{background: '#212121'}} >
                {tabNodes}
            </Tabs>
        );
    }
}

function getTabs(page) {
    var tabs = [];

    for(var i=0; i<page.state.numberOfFinishedRounds; i++) {
        var round = i+1;
        var label = round + '. Forduló';

		const tab = (
            <Tab label={label} key={i} >
                <MatchResults round={round.toString()} />;
            </Tab>
		);
        tabs.push(tab);
    }

    return tabs;
}

export default FinishedMatchResults;