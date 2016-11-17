import React from 'react';
import { Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

const tableStyle = {
    width: '800px',
    marginLeft: 'auto',
    marginRight: 'auto',
    background:'transparent'
};

class MyBets extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
		    bets: [],
		    matches: []
		};
	}

	componentDidMount() {
	    var matchIds = '';
        fetch(window.backendHost + '/api/bets/search/findByUserId?userId=' + window.account.id)
        .then((response) => { return response.json() }).then( (json) => {
            this.setState({bets: json._embedded.bets});

            for(var i=0; i<json._embedded.bets.length; i++) {
                if (i == json._embedded.bets.length - 1) {
                    matchIds += json._embedded.bets[i].matchId;
                } else {
                    matchIds += json._embedded.bets[i].matchId + ',';
                }
            }
        });

        fetch(window.backendHost + '/api/matches/search/findByIdIn?ids=' + matchIds).then((response) => { return response.json() }).then( (json) => {
            this.setState({matches: json._embedded.matches});
        });
	}

    render() {
        var betGainedPoints = [];
        var bets = this.state.bets.map(function(bet) {
            betGainedPoints.push(bet.gainedPoints);
            return bet.homeTeamGoals + ' - ' + bet.awayTeamGoals;
        });

        var matchNodes = this.state.matches.map(function (match, i) {
            var homeTeamName = match.homeTeamName;

            return (
                <TableRow key={i} style={{color: 'white'}} >
                    <TableRowColumn> {i + 1} </TableRowColumn>
                    <TableRowColumn> {match.homeTeamName} - {match.awayTeamName} </TableRowColumn>
                    <TableRowColumn> {match.round} </TableRowColumn>
                    <TableRowColumn> {bets[i]} </TableRowColumn>
                    <TableRowColumn> {match.homeTeamGoals} - {match.awayTeamGoals} </TableRowColumn>
                    <TableRowColumn> {betGainedPoints[i]} </TableRowColumn>
                </TableRow>
            );
        });

        return (
			<div style={{marginTop: '50px'}} >
			    <Table transparent style={tableStyle}>
                    <TableHeader displaySelectAll={false} adjustForCheckbox={false} >
                        <TableRow>
                            <TableHeaderColumn> # </TableHeaderColumn>
                            <TableHeaderColumn> Meccs </TableHeaderColumn>
                            <TableHeaderColumn> Forduló </TableHeaderColumn>
                            <TableHeaderColumn> Tipp </TableHeaderColumn>
                            <TableHeaderColumn> Eredmény </TableHeaderColumn>
                            <TableHeaderColumn> Pont </TableHeaderColumn>
                        </TableRow>
                    </TableHeader>
                    <TableBody displayRowCheckbox={false}>
                        {matchNodes}
				    </TableBody>
                </Table>
			</div>
        );
    }
}

export default MyBets;