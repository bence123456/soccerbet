'use strict';
import React from 'react';

class Matches extends React.Component {

	constructor(props) {
		super(props);
		this.state = {matches: []};
	}

	componentDidMount() {
       fetch('http://localhost:8080/api/matches/search/findByStatus?status=FINISHED')
       .then((response) => { return response.json() })
       .then( (json) => {this.setState({matches: json._embedded.matches}); });
	}

	render() {
		return (
			<MatchList matches={this.state.matches}> </MatchList>
		)
	}
}

class MatchList extends React.Component{
	render() {
		var matches = this.props.matches.map(match =>
			<Match key={match._links.self.href} match={match}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>Home Team</th>
						<th>Away Team</th>
						<th>Home Team Goal</th>
						<th>Away Team Goal</th>
						<th>Round</th>
						<th>Date</th>
					</tr>
					{matches}
				</tbody>
			</table>
		)
	}
}

class Match extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.match.homeTeamName}</td>
				<td>{this.props.match.awayTeamName}</td>
				<td>{this.props.match.homeTeamGoals}</td>
				<td>{this.props.match.awayTeamGoals}</td>
				<td>{this.props.match.round}</td>
				<td>{this.props.match.dateTime}</td>
			</tr>
		)
	}
}

export default Matches;